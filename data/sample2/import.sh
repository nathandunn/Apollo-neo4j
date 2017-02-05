#!/bin/sh
rm -fr /usr/local/Cellar/neo4j/3.0.7/libexec/data/databases/graph.db
neo4j-import --relationships feature-relationship.csv.gz --relationships feature-location.csv.gz --nodes features.csv.gz --nodes sequence.csv.gz --into /usr/local/Cellar/neo4j/3.0.7/libexec/data/databases/graph.db
