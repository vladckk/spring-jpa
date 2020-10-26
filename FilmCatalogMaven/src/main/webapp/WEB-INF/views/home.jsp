<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <style>
                table {
                    font-family: arial, sans-serif;
                    border-collapse: collapse;
                    width: 100%;
                }
                td, th {
                    border: 1px solid #dddddd;
                    text-align: left;
                    padding: 8px;
                }
                tr:nth-child(even) {
                    background-color: #dddddd;
                }
            </style>
    </head>

    <body>
        <table>
            <tr>
                <th>Title</th>
                <th>Genre</th>
                <th>Year</th>
                <th>Actors</th>
                <th>Director</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="movieList" items="${viewMovieList}">
                <tr>
                    <td>${movieList.movie.title}</td>
                    <td>${movieList.genres}</td>
                    <td>${movieList.movie.year}</td>
                    <td>${movieList.actors}</td>
                    <td>${movieList.director}</td>
                    <td><a href="/edit/${movieList.movie.id}">edit</a></td>
                    <td><a href="/delete/${movieList.movie.id}">delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="/add/">Add new film</a>
    </body>
</html>
