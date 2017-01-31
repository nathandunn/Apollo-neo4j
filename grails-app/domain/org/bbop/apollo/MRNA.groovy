package org.bbop.apollo

class MRNA {

    static mapWith = "neo4j"

    static constraints = {
    }

//    id:ID,version,date_created,dbxref_id,description,is_analysis,is_obsolete,last_updated,md5checksum,name,sequence_length,status_id,symbol,unique_name,class:LABEL,alternate_cv_term,class_name,custom_alternate_cv_term,custom_class_name,custom_cv_term,custom_ontology_id,cv_term,meta_data,ontology_id,analysis_feature_id,alteration_residue,deletion_length

    Boolean isAnalysis
    Date lastUpdated
    String uniqueName
    Date dateCreated
    String name
    Long id
    Long version
    Boolean isObsolete

    static mapping = {
        isAnalysis column: "is_analysis"
        lastUpdated column: "last_updated"
        uniqueName column: "unique_name"
        dateCreated column: "date_created"
        isObsolete column: "is_obsolete"
    }
}
