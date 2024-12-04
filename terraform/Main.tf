terraform {
  backend "s3" {
    bucket = "ms-lanchonete-pedido"
    key    = "deployment-service/terraform.tfstate"
    region = "us-east-2"
  }
}

module "mslanchonetepedido" {
  source = "./infra"

  project_name = var.projectname
  region       = var.aws_region
  appversion   = var.app_version
}

variable "aws_region" {
  type        = string
  default     = "us-east-2"
  description = "AWS region"
}

variable "app_version" {
  type        = string
  description = "version to deploy"
}

variable "projectname" {
  type        = string
  default     = "mslanchonetepedido"
  description = "Application Name"
}
