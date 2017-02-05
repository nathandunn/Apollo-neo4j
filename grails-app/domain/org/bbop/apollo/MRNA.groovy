package org.bbop.apollo

class MRNA {

    static mapWith = "neo4j"

    static constraints = {
        id nullable: true
        version nullable: true

        is_analysis nullable: true
        last_updated nullable: true
        unique_name nullable: true
        date_created nullable: true
        name nullable: true
        is_obsolete nullable: true
    }

//    id:ID,version,date_created,dbxref_id,description,is_analysis,is_obsolete,last_updated,md5checksum,name,sequence_length,status_id,symbol,unique_name,class:LABEL,alternate_cv_term,class_name,custom_alternate_cv_term,custom_class_name,custom_cv_term,custom_ontology_id,cv_term,meta_data,ontology_id,analysis_feature_id,alteration_residue,deletion_length

//    Boolean isAnalysis
//    Date lastUpdated
//    String uniqueName
//    Date dateCreated
//    String name
//    Long id
//    Long version
//    Boolean isObsolete
    boolean is_analysis
    Date last_updated
    String unique_name
    Date date_created
    String name
    Long id
    Long version
    Boolean is_obsolete

//    │ue_name: 02abdb85-f427-4e6d-bf│
//    │49-f81bc0f7142b, date_created:│
//    │ 2016-10-03 20:10:21.513, name│
//    │: Group2.19h-00001, id: 351726│
//    │, version: 2, is_obsolete: f

    static mapping = {
//        id column: "__id__"
//        isAnalysis column: "is_analysis"
//        lastUpdated column: "last_updated"
//        uniqueName column: "unique_name"
//        dateCreated column: "date_created"
//        isObsolete column: "is_obsolete"
    }
}
