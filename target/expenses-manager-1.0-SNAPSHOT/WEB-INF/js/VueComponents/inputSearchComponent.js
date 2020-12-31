/**
 * Componente encargado de buscar un gasto en la lista de gastos
 * @author Iordache Mihai Laurentiu
 */
let inputSearch = Vue.component("input-search", {
    template: `
        <div>
            <input type="text" class="search form-control" v-model="searchText" placeholder="What you looking for?">
            {{searchResults}}
        </div>
    `,
    data(){
        return {
            searchText: "",
        }
    },
    computed: {
        searchResults(){
            let searchTerm = this.searchText;

            let searchSplit = searchTerm.replace(/ /g, "'):containsi('")

            $.extend($.expr[':'], {'containsi': function(elem, i, match, array){
                    return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
                }
            });

            $(".results tbody tr.expensesData").not(":containsi('" + searchSplit + "')").each(function(e){
                console.log("not contains")
                $(this).attr('visible','false');
            });

            $(".results tbody tr:containsi('" + searchSplit + "')").each(function(e){
                $(this).attr('visible','true');
            });
        }
    }
});

let vueInstanceInputSearch = new Vue({
    el: "#formInputSearch",
    components: {
        "input-search": inputSearch
    }
});