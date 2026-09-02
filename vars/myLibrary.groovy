def buildApp(String appName) {
    echo "Building application: ${appName}..." //[cite: 1]
    // Build steps here //[cite: 1]
}

def deployApp(String branchName, String appName, String buildNumber) {
    echo 'Deploying to Docker Hub...' //[cite: 1]
    
    // 4. Jenkins Credentials //[cite: 1]
    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) { //[cite: 1]
        
        // Login Docker Hub //[cite: 1]
        sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin' //[cite: 1]
        
        // Build Image For Docker Hub //[cite: 1]
        sh "docker build -t ${DOCKER_USER}/${appName}:${buildNumber} ." //[cite: 1]
        
        // Upload Image Docker Hub //[cite: 1]
        sh "docker push ${DOCKER_USER}/${appName}:${buildNumber}" //[cite: 1]
    }
}

def cleanup() {
    echo 'Cleaning up workspace...' 
    cleanWs() // פקודת ג'נקינס סטנדרטית לניקוי סביבת העבודה בסיום
}
