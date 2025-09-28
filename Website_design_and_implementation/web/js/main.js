$(document).ready(function(){
    $.get("../../VIAPetsGUI/PetList.xml", (xml => {
        $(xml).find("pets").each(function (){
            var petsContainer = $('#petsContainer');

            var name = $(this).find("name").text();
            var age = $(this).find("age").text();
            var color = $(this).find("color").text();
            var price = $(this).find("price").text();
            var gender = $(this).find("gender").text();

            var child = $(` <div class="col-lg col-md-4 col-sm-6 col-12 employee-card">
                <div class="images"><img class="image" src="./assets/dog.jpg" alt="dog Image"></div>
                <div class="Employee_Info">
                    <p class="myname"><b>${name}</b></p>
                    <p>${color}, ${age} years old, ${gender}</p>
                    <p>${price}</p>
                </div>
            </div>`);
            petsContainer.append(child);
        })
    }))

    $.get('../../VIAPetsGUI/PetsInKennel.txt', function(data) {
        var kennelInfoDiv = $('#kennelSpace');
        let spaceOccupied = parseInt(data.trim())
        let spaceLeft = 10 - spaceOccupied
        kennelInfoDiv.text(spaceLeft);
     }, 'text');
})



