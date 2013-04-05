<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
    <jsp:body>
        <div class="container">
            <div class="span6">
                <div class="span12">
                    <h4>Browse all Beers</h4>
                    <a href="/beers" class="btn btn-warning">Show me all beers</a>
                    <hr/>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>