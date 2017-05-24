# Apollo Neo4j
Testing Apollo with Neo4j + Grails3

Just an experiment to assess rough performance of join versus a cypher query.

Run neo4j ```neo4j start``` and change the username / password to neo4j/ demo.

---

Export table from postgresql

    copy feature to 'feature.csv' delimiter ',' CSV HEADER ; 
    copy feature_relationship to 'feature-relationship.csv' delimiter ',' CSV HEADER ; 
    copy feature_location to 'feature-location.csv' delimiter ',' CSV HEADER ; 
    copy sequence to 'sequence.csv' delimiter ',' CSV HEADER ; 


Using this as reference: http://neo4j.com/docs/operations-manual/current/tutorial/import-tool/

Header fixes:

- feature.csv: id:ID,version,date_created,dbxref_id,description,is_analysis,is_obsolete,last_updated,md5checksum,name,sequence_length,status_id,symbol,unique_name,class:LABEL,alternate_cv_term,class_name,custom_alternate_cv_term,custom_class_name,custom_cv_term,custom_ontology_id,cv_term,meta_data,ontology_id,analysis_feature_id,alteration_residue,deletion_length
  - the class is set to :LABEL (otherwise would have to set to Feature)
  - in the class remove org.bbop.apollo so that there is a one-to-one mapping
- sequence.csv: id:ID,version,sequence_end,length,name,organism_id,seq_chunk_size,sequence_start,:LABEL
  - :LABEL is set to SEQUENCE for all in the last column
- feature-relationship.csv: id,version,:START_ID,:END_ID,rank,value,:TYPE
   - :TYPE is set to FEATURE_RELATIONSHIP for all
- feature-locations.csv: id,version,feature_id:START_ID,fmax,fmin,is_fmax_partial,is_fmin_partial,locgroup,phase,rank,residue_info,sequence_id:END_ID,strand,:TYPE
   - :TYPE is set to FEATURE_LOCATION for all


```
    neo4j-import --relationships feature-relationship.csv --relationships feature-location.csv --nodes features.csv --nodes sequence.csv --into /usr/local/Cellar/neo4j/3.0.7/libexec/data/databases/graph.db
```
