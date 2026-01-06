<%-- 
    Document   : index
    Created on : Jan 5, 2026, 5:06:13 PM
    Author     : NQ9
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div style="margin-left: 100px">
            <h1>HTML From</h1>
                <form method="get" action="index.jsp">
                    Textbox <input type="text" name="txtName" value="" size="20"/>
                    <br/>
                    Password <input type="password" name="txtPassword" value="" size="20"/>
                    <br/>
                    Hidden <input type="hidden" name="hdValue" value="123"/>
                    <br/>
                    Male <input type="checkbox" name="chkMale" value="Male"/>
                    <br/>
                    Status
                    <input type="radio" name="status" value="Single"/> Single
                    <input type="radio" name="status" value="Married"/> Married
                    <input type="radio" name="status" value="Divorced"/> Divorced
                    <br/>
                    Combobox
                    <select name="cboSelect">
                        <option value="1">JSP and Servlet</option>
                        <option value="2">EJB</option>
                    </select>
                    <br/>
                    Multiple
                    <select name="mulSelect" multiple size="3">
                        <option value="A">JSP and Servlet</option>
                        <option value="B">EJB</option>
                        <option value="C">Core Java</option>
                    </select>
                    <br/>
                    TextArea
                    <textarea name="txtArea" rows="4" cols="18">
                        This is a form
                        parameters demo!!!
                    </textarea>
                    <br/>
                    <input type="submit" name="txtB"/>
                    <input type="submit" value="Register" name="action"/>
                    <input type="reset" name="txtB"/>
                    <input type="button" value="JavaScript" name="txtB" onclick=""/>
                    <br/>
                    <img src="image/Wed_01b.png"/>
                    <br/><br/>
                </form>
        </div>   
    </body>
</html>
