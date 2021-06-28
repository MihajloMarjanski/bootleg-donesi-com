Vue.component("allUsers",{

    data: function(){
        return{
            users:"",
            searchParmas:{
                firstName:"",
                lastName:"",
                username:"",
                sort:"",
                role:"",
                type:"",
            }
        }
    },
    mounted(){
        axios
        .get('/allUsers')
        .then(response=>{
            this.users = response.data
        })
    },
    template:`
    	<div>
        	<h1>Users</h1>
            <div>
                <input type="text" v-model="searchParmas.firstName" placeholder="Firstname"/>
                <input type="text" v-model="searchParmas.lastName" placeholder="Lastname"/>
                <input type="text" v-model="searchParmas.username" placeholder="Username"/>
                <label for="role"><b>Role</b></label>
                <select name="role" v-model="searchParmas.role" id="role">
                    <option value="CUSTOMER">Customer</option>
                    <option value="MENAGER">Menager</option>
                    <option value="COURIER">Courier</option>
                    <option value="ADMINISTRATOR">Admin</option>
                </select>
                <label for="type"><b>Type</b></label>
                <select name="type" v-model="searchParmas.type" id="type">
                    <option value="NORMAL">Normal</option>
                    <option value="BRONZE">Bronze</option>
                    <option value="SILVER">Silver</option>
                    <option value="GOLD">Gold</option>
                </select>
                <label for="sort"><b>Sort by</b></label>
                <select name="sort" v-model="searchParmas.sort" id="sort">
                    <option value="ASCFNAME">Firstname Ascending</option>
                    <option value="DESCFNAME">Firstname Descending</option>
                    <option value="ASCLNAME">Lastname Ascending</option>
                    <option value="DESCLNAME">Lastname Descending</option>
                    <option value="ASCUNAME">Username Ascending</option>
                    <option value="DESCUNAME">Username Descending</option>
                    <option value="ASCPOINTS">Points Ascending</option>
                    <option value="DESCPOINTS">Points Descending</option>
                </select>
                <button type="button" v-on:click="search">Filter</button>
            </div> 
            <table style="width:99.999%">
                <thead>
                    <th style="width:30%">Name</th>
                    <th style="width:30%">Username</th>
                    <th style="width:30%">Role</th>
                    <th style="width:5%"></th>
                    <th style="width:5%"></th>
                </thead>
                <tbody>
                <tr v-for="u in users">
                   <td style="width:30%">{{u.firstName}} {{u.lastName}}</td>
                   <td style="width:30%">{{u.username}}</td>
                   <td style="width:30%">{{u.role}}</td>
                   <td style="width:5%"><button type= "button" v-on:click="deleteUser(u)">Delete</button> </td>
                   <td style="width:5%"><button type= "button" v-on:click="blockUser(u)">Block</button> </td>
                </tr>
               </tbody>
            </table>
        </div>
    `,
    methods:{
        search(){
            axios
            .post('/searchUsers', this.searchParmas)
            .then(response=>{
                this.users = response.data
            })
            .catch((error) => {
              });
        },

        deleteUser(user){
            alert(user.firstName)
            axios
            .post('/deleteUser', user)
            .then(response=>{
                this.users = response.data
            })
            .catch((error) => {
              });
        },

        blockUser(user){
            alert(user.firstName)
            axios
            .post('/blockUser', user)
            .then(response=>{
                this.users = response.data
            })
            .catch((error) => {
              });
        },
    }

})