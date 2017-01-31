package org.bbop.apollo

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.neo4j.driver.v1.StatementResult
import org.neo4j.driver.v1.Record

@Transactional(readOnly = true)
class MRNAController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        println MRNA.count()
////        respond MRNA.list(params), model:[MRNACount: MRNA.count()]
//        List<MRNA> mrnaList = MRNA.list()
//        MRNA firstMRNA = mrnaList.get(0)
//        println firstMRNA.uniqueName
//        println firstMRNA.name
//        println mrnaList.size()

//        def stuff = MRNA.findAll("MATCH (n:MRNA {name:'YAL022C-00002'})-[:FEATURE_LOCATION]-(q:SEQUENCE {organism_id:'16326'}),(n:MRNA {name:'YAL022C-00002'})-[:RELATIONSHIP]-(p) RETURN n,p,q LIMIT 25")

/n
        StatementResult result = MRNA.cypherStatic("MATCH (n)  RETURN n LIMIT 25")

        List<Record> statementResults = result.list()
        println "# of results: ${statementResults.size()}"
//        for(Record r in statementResults){
//            println r.keys()
//        }

//        def club = Club.find("MATCH n where n.name = {1} RETURN n", 'FC Bayern Muenchen')


        respond model:[MRNACount:MRNA.count(),dog:"cat"]
    }

    def show(MRNA MRNA) {
        respond MRNA
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

    def edit(MRNA MRNA) {
        respond MRNA
    }

    @Transactional
    def update(MRNA MRNA) {
        if (MRNA == null) {
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
