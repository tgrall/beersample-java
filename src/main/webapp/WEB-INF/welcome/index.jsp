<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
    <jsp:body>
        <div class="span6">
            <div class="span12">
                <h4>Browse all Beers</h4>
                <a href="/beers" class="btn btn-warning">Show me all beers</a>
                <hr/>
            </div>
            <div class="span12">
                <h4>Browse all Breweries</h4>
                <a href="/breweries" class="btn btn-info">Take me to the breweries</a>
                <hr/>
            </div>
        </div>
    </jsp:body>
</t:layout>