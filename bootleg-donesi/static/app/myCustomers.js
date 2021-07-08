Vue.component("myCustomers",{

    data: function(){
        return{
            getFor:{
                username: "",
            },
            customers:""
        }
    },
    mounted(){
        this.getFor.username = localStorage.getItem('username')
        axios
        .post('/getMyCustomers',this.getFor)
        .then(response=>{
            this.customers = response.data
        })
    },
    template:`
    	<div>
        <h1>Customers</h1>
        <div>
            <table style="width:99.999%">
                <thead>
                    <th style="width:30%">Username</th>
                    <th style="width:30%">Name</th>
                    <th style="width:30%">Type</th>
                </thead>
                <tbody>
                    <tr class="nopointerrow" v-for="c in customers">
                        <td style="width:30%"> {{c.username}}</td>
                        <td style="width:30%">{{c.firstName}} {{c.lastName}}</td>
                        <td style="width:30%">{{c.customerType.typeName}}</td>
                    </tr>                                                                  
                </tbody>
            </table>            
        </div>
        </div>

    `,
    methods:{
    }

})