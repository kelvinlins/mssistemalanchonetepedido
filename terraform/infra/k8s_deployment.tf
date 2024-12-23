data "aws_ecr_repository" "repository" {
  name = "${var.project_name}-repository"
}


resource "kubernetes_deployment" "deployment" {
  metadata {
    name = "${var.project_name}-deployment"
  }
  spec {
    replicas = 2
    selector {
      match_labels = {
        app = var.project_name
      }
    }
    template {
      metadata {
        labels = {
          app = var.project_name
        }
      }
      spec {
        container {
          image = "${data.aws_ecr_repository.repository.repository_url}:${var.appversion}"
          name  = var.project_name
          port {
            container_port = 8080
          }
          resources {
            limits = {
              cpu    = "0.5"
              memory = "512Mi"
            }
            requests = {
              cpu    = "250m"
              memory = "50Mi"
            }
          }

          env {
            name  = "SPRING_DATASOURCE_URL"
            value = "jdbc:postgresql://pedido.ch2a4s0muanr.us-east-2.rds.amazonaws.com:5432/pedido"
          }

          env {
            name  = "SPRING_DATASOURCE_USERNAME"
            value = "postgres"
          }

          env {
            name  = "SPRING_JPA_HIBERNATE_DDL_AUTO"
            value = "update"
          }

          env {
            name = "SPRING_DATASOURCE_PASSWORD"
            value = "postgres"
          }

          env {
            name = "TOKEN_SECRET"
            value = "53080375-3f93-4f8c-96ec-d33a440dc6b0"
          }
          # liveness_probe {
          #   http_get {
          #     path = "/"
          #     port = 8080
          #   }

          #   initial_delay_seconds = 3
          #   period_seconds        = 3
          # }
        }
      }
    }
  }
}
