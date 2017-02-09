<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'MRNA.label', default: 'MRNA')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-MRNA" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-MRNA" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ul>
                <li>Total avg time (ms): ${avgTime}</li>
                <li>Total avg query time (ms): ${avgQueryTime}</li>
                <li>Feature Name: ${featureName}</li>
            </ul>
            <f:table collection="${MRNAList}" />

            %{--SIZE: [${model.MRNACount}, ${model.dog}]--}%

            <div class="pagination">
                <g:paginate total="${MRNACount ?: 0}" />
            </div>
        </div>
    </body>
</html>