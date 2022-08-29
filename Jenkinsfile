pipeline {
  agent any
  stages {
    stage("build") {
      speps {
        echo 'building the application...'
      }
    }
    stage("test") {
      speps {
        echo 'testing the application...'
      }
    }
    stage("deploy") {
      speps {
        echo 'deploying the application...'
      }
    }
  }
}
