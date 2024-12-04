data "aws_vpc" "mslanchonetepedido_vpc" {
  filter {
    name   = "tag:Name"
    values = ["${var.project_name}-vpc"]
  }
}

data "aws_subnets" "mslanchonetepedido_public_subnets" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.mslanchonetepedido_vpc.id]
  }

  filter {
    name   = "tag:type"
    values = ["public"]
  }
}
