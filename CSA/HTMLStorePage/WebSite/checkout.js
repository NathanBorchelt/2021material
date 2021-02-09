function produceList(){
    var allOrder = localStorage.getItem("order")
    var splitOrder = allOrder.split("\t")
    var produceTable = "<tr>\n\t"
    for(var i = 0; i < splitOrder.length; i++){
        var split = splitOrder[i].split(" ")
        produceTable += "<td>" + split[0] + "</td>\n"
    }
    produceTable += "</tr>"
}