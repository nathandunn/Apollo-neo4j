package org.bbop.apollo

import org.neo4j.driver.internal.InternalNode

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.neo4j.driver.v1.StatementResult
import org.neo4j.driver.v1.Record

@Transactional(readOnly = true)
class MRNAController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def showAll(Integer max){
        params.max = Math.min(max ?: 10, 1000000000)
        String sequenceName = params.sequenceName ?: "Group2.19"
        String featureName = params.featureName ?: "*"

        long startTime = System.currentTimeMillis()

        String query = "MATCH (n:MRNA )-[o:FEATURE_LOCATION]-(q:SEQUENCE {name:'${sequenceName}'}),(n:MRNA)-[r:RELATIONSHIP]-(p) RETURN n,p,q,o LIMIT ${params.max}"
        StatementResult result = MRNA.cypherStatic(query)

        List<Map> resultList = new ArrayList<>()

        while(result.hasNext()){
            Record record = result.next()
            resultList.add(record.asMap())
        }
        long stopTime = System.currentTimeMillis()

        respond model:[results:resultList,MRNACount:resultList.size(),time:(stopTime-startTime)]

    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

//        def stuff = MRNA.findAll("MATCH (n:MRNA {name:'YAL022C-00002'})-[:FEATURE_LOCATION]-(q:SEQUENCE {organism_id:'16326'}),(n:MRNA {name:'YAL022C-00002'})-[:RELATIONSHIP]-(p) RETURN n,p,q LIMIT 25")
        List<Long> queryTimes = new ArrayList<Long>()
        List<Long> retrievalTimes = new ArrayList<Long>()
        for(int i = 0 ; i < 10 ; i++){
            long startTime = System.currentTimeMillis()
//            String query = "MATCH (n:MRNA {name:'YAL022C-00002'})-[:FEATURE_LOCATION]-(q:SEQUENCE),(n:MRNA {name:'YAL022C-00002'})-[:RELATIONSHIP]-(p) RETURN n,p,q LIMIT 25"
            String query = "MATCH (n:MRNA {name:'Group2.19h-00001'})-[:FEATURE_LOCATION]-(q:SEQUENCE),(n:MRNA {name:'Group2.19h-00001'})-[:RELATIONSHIP]-(p) RETURN n,p,q LIMIT ${params.max}"
//            StatementResult result = MRNA.cypherStatic("MATCH (n:MRNA {name:'YAL022C-00002'})-[:FEATURE_LOCATION]-(q:SEQUENCE {organism_id:'16326'}),(n:MRNA {name:'YAL022C-00002'})-[:RELATIONSHIP]-(p) RETURN n,p,q LIMIT 25")
            StatementResult result = MRNA.cypherStatic(query)
            long stopTime = System.currentTimeMillis()
            queryTimes.add(stopTime - startTime)
            startTime = System.currentTimeMillis()
            List<Record> statementResults = result.list()
            stopTime = System.currentTimeMillis()
            retrievalTimes.add(stopTime - startTime)
            println "# of results: ${statementResults.size()}"
        }
        Double avgQueryTime = queryTimes.sum() / queryTimes.size()
        println "avg query time ${avgQueryTime}"
        Double avgRetrievalTime = retrievalTimes.sum() / retrievalTimes.size()
        println "avg retrieval time ${avgRetrievalTime}"

//        for(Record r in statementResults){
//            println r.keys()
//        }

//        def club = Club.find("MATCH n where n.name = {1} RETURN n", 'FC Bayern Muenchen')


        respond model:[MRNACount:MRNA.count(),dog:"cat"]
    }

    def getOne() {
        respond MRNA.findByName("Group2.19h-00001"), view: 'show'
    }

    def show(MRNA mrna) {
        respond mrna
    }

    def create() {
        respond new MRNA(params)
    }

    @Transactional
    def save(MRNA MRNA) {
        if (MRNA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (MRNA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond MRNA.errors, view:'create'
            return
        }

        MRNA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'MRNA.label', default: 'MRNA'), MRNA.id])
                redirect MRNA
            }
            '*' { respond MRNA, [status: CREATED] }
        }
    }

    def edit(MRNA mrna) {
        respond mrna
    }

    @Transactional
    def update(MRNA mrna) {
        if (mrna == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (MRNA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond MRNA.errors, view:'edit'
            return
        }

        MRNA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MRNA.label', default: 'MRNA'), MRNA.id])
                redirect MRNA
            }
            '*'{ respond MRNA, [status: OK] }
        }
    }

    @Transactional
    def delete(MRNA MRNA) {

        if (MRNA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        MRNA.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MRNA.label', default: 'MRNA'), MRNA.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'MRNA.label', default: 'MRNA'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
