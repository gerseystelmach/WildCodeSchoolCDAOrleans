<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <title>Spring Demo application</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    
        <style>
            #knight_banner { 
                display: block;
                margin-left: auto;
                margin-right: auto;
            }
        </style>
    </head>

    <body>
        <img id="knight_banner"  src="../resources/images/knights_of_java.jpg"/>

        <ul id="knight_list">
            <c:forEach items="${knightList}" var="knight">
                <li>${knight}</li>
            </c:forEach>
        </ul>
    </body>
</html>
