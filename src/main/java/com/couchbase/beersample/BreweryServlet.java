/**
 * Copyright (C) 2009-2012 Couchbase, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALING
 * IN THE SOFTWARE.
 */

package com.couchbase.beersample;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.protocol.views.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.spy.memcached.internal.OperationFuture;

/**
 * The BreweryServlet handles all Brewery-related HTTP Queries.
 *
 * The BreweryServlet is used to handle all HTTP queries under the /breweries
 * namespace. The "web.xml" defines a wildcard route for every /breweries/*
 * route, so the doGet() method needs to determine what should be dispatched.
 */
public class BreweryServlet extends HttpServlet {

  /**
   * Obtains the current CouchbaseClient connection.
   */
  final CouchbaseClient client = ConnectionManager.getInstance();

  /**
   * Google GSON is used for JSON encoding/decoding.
   */
  final Gson gson = new Gson();

  /**
   * Dispatch all incoming GET HTTP requests.
   *
   * Since the /breweries/* routes are wildcarded and will all end up here, the
   * method needs to check agains the PATH (through getPathInfo()) and
   * determine which helper method should be called. The helper method then
   * does the actual request and response handling.
   *
   * @param request the HTTP request object.
   * @param response the HTTP response object.
   * @throws ServletException
   * @throws IOException
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    try {
      if(request.getPathInfo().startsWith("/show")) {
        handleShow(request, response);
      } else if(request.getPathInfo().startsWith("/delete")) {
        handleDelete(request, response);
      }
    } catch (InterruptedException ex) {
      Logger.getLogger(BreweryServlet.class.getName()).log(
        Level.SEVERE, null, ex);
    } catch (ExecutionException ex) {
      Logger.getLogger(BreweryServlet.class.getName()).log(
        Level.SEVERE, null, ex);
    }
  }

  /**
   * Handle the /breweries/show/<BREWERY-ID> action
   *
   * This method loads up a document based on the given brewery id.
   *
   * @param request the HTTP request object.
   * @param response the HTTP response object.
   * @throws IOException
   * @throws ServletException
   */
  private void handleShow(HttpServletRequest request,
    HttpServletResponse response) throws IOException, ServletException {

    String breweryId = request.getPathInfo().split("/")[2];
    String document = (String) client.get(breweryId);
    if(document != null) {
      HashMap<String, String> brewery = gson.fromJson(document, HashMap.class);
      request.setAttribute("brewery", brewery);
    }

    request.getRequestDispatcher("/WEB-INF/breweries/show.jsp")
      .forward(request, response);
  }

  /**
   * Handle the /breweries/delete/<BREWERY-ID> action
   *
   * This method deletes a brewery based on the given brewery id.
   *
   * @param request the HTTP request object.
   * @param response the HTTP response object.
   * @throws IOException
   * @throws ServletException
   * @throws InterruptedException
   * @throws ExecutionException
   */
  private void handleDelete(HttpServletRequest request,
    HttpServletResponse response) throws IOException, ServletException,
    InterruptedException,
    ExecutionException {

    String breweryId = request.getPathInfo().split("/")[2];
    OperationFuture<Boolean> delete = client.delete(breweryId);

    if(delete.get()) {
      response.sendRedirect("/breweries");
    }
  }

}
