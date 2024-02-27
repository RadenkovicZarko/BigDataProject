# Real-Time Data Processing System with Kafka, Spark, and Databricks
## Overview
This project demonstrates the development of a system for processing (near) real-time data using Apache Kafka, Apache Spark, and the Databricks platform. It encompasses the collection of real-time data from various APIs, processing and storing this data, and conducting analyses and visualizations within a Databricks environment. The project aims to showcase the application of big data technologies in extracting insights from real-time data streams.

## Features
Data Collection: Utilizes Apache Kafka and a custom Java Producer to collect real-time data from multiple APIs.
Data Processing: Implements a Java Consumer to consume data from Kafka topics and store it in CSV format.
Data Analysis and Visualization: Imports the collected data into Databricks, performing analyses, and visualizations using DataFrames, Spark SQL, and other Databricks tools.
Spark Streaming: Applies Spark Streaming to the data for real-time analysis and visualization.
Predictive Analytics: Conducts regression analyses to predict future values based on the collected data.

## APIs Used
Alpha Vantage Financial Data: https://www.alphavantage.co/documentation

## Setup Instructions
### Prerequisites
Apache Kafka setup for data streaming.
Access to the Databricks platform.
Java environment for running Kafka Producer and Consumer.
Python environment in Databricks for analysis and visualization.

## Running the Project

### Data Collection:

Configure and start the Kafka Producer to collect data from the specified APIs and publish to Kafka topics.
Start the Kafka Consumer to consume data from the topics and store it in CSV format in the /data directory.
Data Analysis with Databricks:

### Import the CSV files into Databricks.
Execute the notebooks in /notebooks to perform data analysis, visualization, and Spark Streaming.

## Analyses Conducted
Statistical tests to understand data distributions and relationships.
Regression analysis to predict future values based on historical data.
Real-time data analysis and visualization using Spark Streaming.


## Conclusion
This project leverages modern big data technologies to process and analyze real-time data, providing insights that could be valuable for decision-making in various domains. Through the use of Apache Kafka, Spark, and Databricks, it demonstrates a scalable approach to handling streaming data, predictive analytics, and real-time analysis.
