/**
 * Este componente representa el botón borrar de los gastos
 * @author Iordache Mihai Laurentiu
 * @version 1.0
 */
let deleteBtn = Vue.component("delete-btn", {
    template: `
        <div>
            <tr>
                <button @click="deleteObj" class="btn btn-success">Delete</button>
            </tr>
        </div>
    `,
    props: {
      refObjectId: {type: Number, default: null, required: true}
    },
    methods:{
        deleteObj: function(){
            let token = document.querySelector("meta[name='_csrf']").getAttribute("content");

            axios.delete(`../webapi/delete/${this.refObjectId}?_csrf=${token}`)
                .then(() => window.location = "../expenses/manage");
        }
    }
});