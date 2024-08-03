pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'registry.hub.docker.com/andy999' // Your Docker registry URL
        IMAGE_NAME = 'shareserviceproj' // Base name of your Docker image
        GCP_SERVICE_ACCOUNT_KEY = credentials('gcp-service-account-key') // Jenkins credentials ID
        GITHUB_TOKEN = credentials('github-token') // Reference the token stored in Jenkins
        PROJECT_ID = 'my-first-project-150619'
        CLUSTER_NAME = 'your-gke-cluster-name'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout from GitHub repository using the personal access token
                    checkout([$class: 'GitSCM', branches: [[name: '*/default']],
                              doGenerateSubmoduleConfigurations: false,
                              extensions: [],
                              submoduleCfg: [],
                              userRemoteConfigs: [[url: 'https://github.com/sysanmol/ShareMarketService.git',
                                                   credentialsId: 'github-token']]])
                }
            }
        }

        stage('Debug Directory') {
            steps {
                script {
                    // Print the current directory and list its contents
                    echo 'Debug Directory'
                    sh 'pwd'
                    sh 'ls -la'
                }
            }
        }

        stage('Extract Version') {
            steps {
                dir('ShareMarketServices') {
                    script {
                        // Extract the version from pom.xml using properties-maven-plugin
                        def version = sh(script: """
                        mvn help:evaluate -Dexpression=project.version -q -DforceStdout
                    """, returnStdout: true).trim()

                        // // Ensure the version extracted is not just a single digit
                        // if (version ==~ /\d+/) {
                        //     error "Failed to extract the full version. Extracted version: ${version}"
                        // }

                        env.IMAGE_TAG = version // Set the image tag dynamically

                        // Print the extracted version
                        echo "Extracted version: ${version}"
                    }
                }
            }
        }

        stage('Build Docker Image with Jib') {
            steps {
                dir('ShareMarketServices') { // Adjusted to include the 'sharefolder' directory
                    script {
                        withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                            // Build Docker image using Jib Maven plugin with Docker Hub credentials
                            sh """
                                mvn compile jib:build \
                                -Dimage=${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} \
                                -Djib.to.auth.username=${DOCKER_USERNAME} \
                                -Djib.to.auth.password=${DOCKER_PASSWORD}
                            """
                        }
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // The image is pushed as part of the jib:build command, no additional steps are needed here.
                    echo "Image ${DOCKER_REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG} built and pushed successfully."
                }
            }
        }

    }
}
