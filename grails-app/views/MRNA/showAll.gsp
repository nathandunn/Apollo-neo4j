<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'MRNA.label', default: 'MRNA')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-MRNA" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<h2 id="list-MRNA" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <h2 class="col-sm-offset-1 header">
        Elapsed ${model.time} ms
        SIZE: [${model.MRNACount}]
    </h2>
    <ul class="col-sm-offset-1">
        <g:each in="${model.results}" var="record">
        %{--${record}--}%
            <li>Entry
                <ul class="col-sm-offset-1">
                    <g:each in="${record}" var="inner">
                        <li>
                            ${inner.key} -> ${inner.value}
                            <ul class="col-sm-offset-1">
                                <g:each in="${inner.value.asMap()}" var="inval">
                                    <li>${inval.key} -> ${inval.value}</li>
                                </g:each>
                            </ul>
                        </li>
                    </g:each>
                </ul>
            </li>
        </g:each>
    </ul>

    SIZE: [${model.MRNACount}]

    %{--<div class="pagination">--}%
    %{--<g:paginate total="${MRNACount ?: 0}" />--}%
    %{--</div>--}%
</div>
</body>
</html>