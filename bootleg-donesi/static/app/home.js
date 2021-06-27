Vue.component("home",{

    data: function(){
        return{
            restaurants:"",
            id:"",
            role:"",

        }
    },
    mounted(){
        this.role = localStorage.getItem('role')
        this.id = localStorage.getItem('id')
        console.log(this.role)
        console.log(this.id)
        axios
        .get('/allRestaurants')
        .then(response=>{
            this.restaurants = response.data
        })
    },
    template:`
        <div>
    	    <div v-if="(!role)" >
            	<h1>BOOTLEG DONESI</h1>
            	<button type= "button" v-on:click="register">Register</button>
            	<button type= "button" v-on:click="login">Login</button>
            </div>
            <div v-if="role === 'ADMINISTRATOR'">
                <h1>DOBRODOSO GAZDA</h1>
                <button type= "button" v-on:click="registerEmployee">Register Employee</button>
                <button type= "button" v-on:click="showUsers">Users</button>
            </div>
            <h1>Restaurant list</h1> 
            <table style="width:100%">
                <thead>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Location</th>
                    <th>Logo</th>
                </thead>
                <tbody>
                <tr v-for="r in restaurants">
                   <td style="width:10%">{{r.name}}</td>
                   <td style="width:10%">{{r.type}}</td>
                   <td style="width:10%">{{r.location.adress.street}}, {{r.location.adress.town}}</td>
                   <td style="width:30%"><img :src="r.logoPath" width="75" height="75" ></td>
                </tr>
               </tbody>
            </table>
        </div>
    `,
    methods:{
        register(){
            this.$router.push("/register")
        },
        login(){
            this.$router.push("/login")
        },
        registerEmployee(){
            this.$router.push("/registerEmployee")
        },
        showUsers(){
            this.$router.push("/allUsers")
        }

    }

})