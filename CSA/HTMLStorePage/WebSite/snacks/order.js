function pageName(){
    var path = window.location.pathname
    var page = path.split("/").pop()
    return page
}

function addToOrder(fullName,quantityId,costId){
    var allStr = "itemsInOrder"
    var numberBuying = document.getElementById(quantityId).value
    var pageNameVar = pageName()
    var itemList = fullName+" "+document.getElementById(costId).innerHTML+" "+numberBuying
    try{
        console.log("reasonable etry")
        if(localStorage.getItem(allStr).includes(pageNameVar)){
            console.log("tryif")
            var addedItem = localStorage.getItem(pageNameVar)
            console.log(addedItem)
            var itemSplit = addedItem.split(" ")
            console.log(itemSplit)
            itemSplit[2] = Number(itemSplit[2])+Number(numberBuying)
            itemList = itemSplit[0]+" "+itemSplit[1]+" "+itemSplit[2]
            console.log(itemSplit[0]+" "+itemSplit[1]+" "+itemSplit[2])            
        }
        else{
            console.log("tryelse")
            
            //
            
            localStorage.setItem(allStr,localStorage.getItem(allStr)+" "+pageNameVar)
        }
    }
    catch(error){
        //console.error(error)
        localStorage.setItem(allStr,pageNameVar)
        console.log("reasonable error")
    }    
    //console.log("addes item"+pageName())
    localStorage.setItem(pageNameVar,itemList)
    console.log(localStorage)
}


function clrMem(){
    localStorage.clear()
}