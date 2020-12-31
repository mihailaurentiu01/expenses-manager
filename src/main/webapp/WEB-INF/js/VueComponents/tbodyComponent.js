/**
 * Este componente se encarga de hacer una petición a la api y rellena los datos de la tabla
 * @author Iordache Mihai Laurentiu
 */

let tBody = Vue.component("tBody", {
   template: `
        <tbody>    
            <tr class="expensesData" v-for="(obj, index) in data">
                <td>{{++index}}</td>
                <td>{{obj.name}}</td>
                <td>{{obj.description}}</td>
                <td>{{obj.quantity}}</td>
                <td>{{new Date(obj.boughtOn).getDate()}}/{{new Date(obj.boughtOn).getMonth()+1}}/{{new Date(obj.boughtOn).getFullYear()}}</td>
                <td>{{obj.price}} $</td>
              
                <td>
                    <delete-btn :refObjectId="obj.id"></delete-btn>
                </td>
            </tr>
            
            <h3 v-if="loading">Loading...</h3>
        </tbody>
   `,
    data(){
       return {
           data: null,
           loading: true
       }
    },
    beforeMount(){
        axios.get("../webapi/expenses")
            .then(response => {
                this.data = response.data;
                this.loading = false;
            })

    },
    components: {
       "delete-btn": deleteBtn
    }
});

let vueInstanceTBody =  new Vue({
    el: ".results",
    components: {
        "tBody": tBody
    }
});



