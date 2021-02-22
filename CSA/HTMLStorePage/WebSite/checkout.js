function prdcList(){
    var itemsInOrder = localStorage.getItem("itemsInOrder")
    var arrayOrder = itemsInOrder.split(" ")
    var tableHTML = ""
    for(var itemIndex = 0; itemIndex < arrayOrder.length; itemIndex++){
        tableHTML += "<tr>"
        var itemAtIndexInformation = localStorage.getItem(arrayOrder[itemIndex])
        var arrayItemAtIndexInformaion = itemAtIndexInformation.split(" ")
        for(var itemInfoIndex = 0; itemInfoIndex < arrayItemAtIndexInformaion.length;itemInfoIndex++){
            tableHTML +="<td>"+arrayItemAtIndexInformaion[itemInfoIndex]+"</td>"
        }
        tableHTML+="</tr>"
    }
    document.getElementById("restOfOrder").innerHTML = tableHTML
}