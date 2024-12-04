terraform {
  backend "s3" {
    bucket = "ms-lanchonete-pedido"
    key    = "infra-eks/terraform.tfstate"
    region = "us-east-2"
  }
}

module "mslanchonetepedido" {
  source = "./infra"

  project_name = var.projectname
  region       = var.aws_region
}

variable "aws_region" {
  type        = string
  default     = "us-east-2"
  description = "AWS region"
}

variable "projectname" {
  type        = string
  default     = "mslanchonetepedido"
  description = "Application Name"
}
