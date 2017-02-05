#!/bin/sh
rm -fr /usr/local/Cellar/neo4j/3.0.7/libexec/data/databases/graph.db
neo4j-import --relationships feature-relationship.csv --relationships feature-location.csv --nodes features.csv --nodes sequence.csv --into /usr/local/Cellar/neo4j/3.0.7/libexec/data/databases/graph.db
