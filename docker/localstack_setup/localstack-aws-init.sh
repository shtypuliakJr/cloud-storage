#!/bin/bash
echo "AWS INIT: Configuring localstack components..."

aws --endpoint-url=http://cs-localstack:4566 --region=us-east-1 sqs create-queue --queue-name cs-local-notification-message-queue

echo "AWS INIT: Components have been configured."
