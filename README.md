# HDInsight  Flink Examples

## Prerequisite

- [Subscription prerequisites](https://learn.microsoft.com/en-us/azure/hdinsight-aks/prerequisites-subscription)
- [Resource prerequisites](https://learn.microsoft.com/en-us/azure/hdinsight-aks/prerequisites-resources)

## Create an Apache Flink Cluster

[Create a cluster pool](https://learn.microsoft.com/en-us/azure/hdinsight-aks/quickstart-create-cluster#create-a-cluster-pool)
[Create an Apache Flink Cluster](https://learn.microsoft.com/en-us/azure/hdinsight-aks/flink/flink-create-cluster-portal)


For the Streaming example, we need to add the following additional resources:

1. [Create HDInsight Kafka Cluster](https://learn.microsoft.com/en-us/azure/hdinsight/kafka/apache-kafka-get-started) - Select the Virtual Network created in Step#3
2. Create Bastion VM; this is required to create a Kafka topic and console consumer - Select the Virtual Network created in Step#3

## Code Setup

### Prerequisite

- maven (>=3.6) is installed and configured - [download](https://maven.apache.org/download.cgi#:~:text=Downloading%20Apache%20Maven%203.8.6,recommended%20version%20for%20all%20users.)
- [Java 11](https://learn.microsoft.com/en-us/java/openjdk/download) and JAVA_HOME is setup

### Build Code

This repository has multi modules:

- Batch - Examples of Flink Batch Processing
- KafkaStream - Examples of Flink Kafka Stream processing 

You can clone this repository  - `git clone https://github.com/sethiaarun/hdiflinkexample.git` or [using SSH](https://docs.github.com/en/authentication/connecting-to-github-with-ssh) `git clone git@github.com:sethiaarun/hdiflinkexample.git` -

To build the code run `mvn clean compile package`

## Running Flink Examples

- [Running Batch Examples](batch-examples/README.md)

- [Running Kafka Producer Example](kafkastream-examples/README.md)
