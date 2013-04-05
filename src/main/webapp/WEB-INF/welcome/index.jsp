<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
    <jsp:body>
        <div class="container">
            <h1>Welcome to the Couchbase Workshop</h1>
            <p class="lead">Starting point of your application. Let's now add some data to this beautiful application</p>
            <p class="lead">Cheers!</p>

            <div class="row" class="span6">
                <div class="span6">
                <pre>${beer}</pre>

                Edit this beer : <a href="/beers/edit/blue_ridge_brewing-colonel_paris_pale_ale">/beers/edit/blue_ridge_brewing-colonel_paris_pale_ale</a>

                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>