<html lang="en">
<head>
    <title>Latest</title>
</head>
<body>
<h1>Latest snippets</h1>
<table>
    <tr>
        <th>Date</th>
        <th>Code</th>
    </tr>
    <#list snippets as snippet>
        <tr>
            <td><span id="load_date">${snippet.date}</span></td>
            <td><pre id="code_snippet">${snippet.code}</pre></td>
        </tr>
    </#list>
</table>
</body>
</html>