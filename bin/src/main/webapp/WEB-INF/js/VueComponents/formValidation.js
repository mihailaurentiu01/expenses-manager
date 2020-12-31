/**
 * Componente encargado de validar el formulario para añadir un gasto
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */

let vueInstanceForm = new Vue({
    el: "#form",
    data: {
        itemName: "",
        itemQuantity: 1,
        itemPrice: 0.01,
        itemDescription: "",
        errors: [],
        token: document.querySelector("meta[name='_csrf']").getAttribute("content")

    },
    methods: {
        validate: function(){
            this.errors = [];

            let boughtOn = document.getElementById("datepicker").value;

            if (this.itemPrice < 0 ){
                this.errors.push("You must specify a valid expense price");
            }

            if (boughtOn === ""){
                this.errors.push("You must specify the date you bought the item");
            }

            if (this.errors.length === 0){
                boughtOn = boughtOn.split("-");

                let expense = {
                  id: 0,
                  name: this.itemName,
                  description: this.itemDescription,
                  price: this.itemPrice,
                  boughtOn: new Date(boughtOn[2], parseInt(boughtOn[1])-1, boughtOn[0]).toISOString(),
                  quantity: this.itemQuantity,
                  user: null
                };

                vueInstanceForm.addExpense(expense);
            }


        },
        addExpense: function(expense){
            console.log(expense)
            axios.post(`../webapi/new?_csrf=${this.token}`, expense)
                .then(() => window.location = "../expenses/manage")
        }
    }
})