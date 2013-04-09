<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
    <jsp:body>
        <h3>${title}</h3>

        <c:if test="${msg != null}">
            <div class="alert alert-danger text-error">${msg}</div>
        </c:if>

        <form method="post" action="/beers/edit/${beer.id}">
            <input type="hidden" value="${cas}" name="cas" />
            <fieldset>
              <legend>General Info</legend>
              <div class="span12">
                <div class="span6">
                  <label>Name</label>
                  <input type="text" name="beer_name" placeholder="The name of the beer." value="${beer.name}">

                  <label>Description</label>
                  <input type="text" name="beer_description" placeholder="A short description." value="${beer.description}">
                </div>
                <div class="span6">
                  <label>Style</label>
                  <input type="text" name="beer_style" placeholder="Bitter? Sweet? Hoppy?" value="${beer.style}">

                  <label>Category</label>
                  <input type="text" name="beer_category" placeholder="Ale? Stout? Lager?" value="${beer.category}">
                </div>
              </div>
            </fieldset>
            <fieldset>
                <legend>Details</legend>
                <div class="span12">
                    <div class="span6">
                      <label>Alcohol (ABV)</label>
                      <input type="text" name="beer_abv" placeholder="The beer's ABV" value="${beer.abv}">

                      <label>Bitterness (IBU)</label>
                      <input type="text" name="beer_ibu" placeholder="The beer's IBU" value="${beer.ibu}">
                    </div>
                    <div class="span6">
                      <label>Beer Color (SRM)</label>
                      <input type="text" name="beer_srm" placeholder="The beer's SRM" value="${beer.srm}">

                      <label>Universal Product Code (UPC)</label>
                      <input type="text" name="beer_upc" placeholder="The beer's UPC" value="${beer.upc}">
                    </div>
                </div>
                <div class="span12">
                    <label>Comment</label>
                    <textarea class="input-xxlarge" name="beer_comments" rows="3">${beer.comment}</textarea>
                </div>

                <div class="span12">
                    <label>Product Page</label>
                    <input class="input-xlarge" type="url" name="beer_product_page"  value="${beer.product_page}">
                </div>

            </fieldset>
            <fieldset>
                <legend>Brewery</legend>
                <div class="span12">
                    <div class="span6">
                      <label>Brewery</label>
                      <input type="text" name="beer_brewery_id" placeholder="The brewery" value="${beer.brewery_id}">
                    </div>
                </div>
            </fieldset>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Save changes</button>
            </div>
        </form>
    </jsp:body>
</t:layout>