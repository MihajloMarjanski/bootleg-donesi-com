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
        	<h1>User list</h1>
            <div style="border:1px solid black">
                <h2>Filter</h2>
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
            <table style="width:100%">
                <thead>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Role</th>
                    <th></th>
                    <th></th>
                </thead>
                <tbody>
                <tr v-for="u in users">
                   <td>{{u.firstName}} {{u.lastName}}</td>
                   <td><a>{{u.username}}</a></td>
                   <td>{{u.role}}</td>
                   <td><button type= "button" v-on:click="deleteUser(u)">Delete</button> </td>
                   <td><button type= "button" v-on:click="blockUser(u)">Block</button> </td>
                </tr>
               </tbody>
            </table>
            <div>
                <button type= "button" v-on:click="cancel">Cancel</button>
            </div>
        </div>
    `,
    methods:{
        cancel(){
            this.$router.push("/")
        },

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