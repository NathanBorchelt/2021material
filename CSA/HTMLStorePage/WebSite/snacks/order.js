function addToOrder(srtName,fullName,quantityId){
    localStorage.setItem("itemsInOrder",localStorage.getItem("itemsInOrder")+" "+srtName)
    var itemList = fullName+" "+document.getElementById("priceOfItem")+" "+document.getElementById(quantityId)
    localStorage.setItem(srtName,itemList)

}

function pageName(){
    var path = window.location.pathname
    var page = path.split("/").pop()
    return page
}