<%@ page import="java.util.List, br.com.loja.model.Cliente" %>

<%
    List<Cliente> clientes =
        (List<Cliente>) request.getAttribute("clientes");
%>

<table border="1">
<tr>
    <th>ID</th><th>Nome</th><th>CPF</th><th>Email</th>
</tr>

<% for (Cliente c : clientes) { %>
<tr>
    <td><%= c.getIdCliente() %></td>
    <td><%= c.getNome() %></td>
    <td><%= c.getCpf() %></td>
    <td><%= c.getEmail() %></td>
</tr>
<% } %>
</table>
