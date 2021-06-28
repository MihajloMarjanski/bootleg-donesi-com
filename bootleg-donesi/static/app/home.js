Vue.component("home",{

    data: function(){
        return{
            id:"",
            role:"",
            username:"",
            window:"RESTAURANTS"

        }
    },
    mounted(){
        this.role = localStorage.getItem('role')
        this.id = localStorage.getItem('id')
        this.username = localStorage.getItem('username')
    },
    template:`
        <div>
    	    <div v-if="(!role)" >
            	<button type= "button" v-on:click="register">Register</button>
            	<button type= "button" v-on:click="login">Login</button>
            </div>
            <div v-if="role === 'ADMINISTRATOR'">
                <button type= "button" v-on:click="restaurants">Restaurants</button>
                <button type= "button" v-on:click="registerEmployee">Register Employee</button>
                <button type= "button" v-on:click="showUsers">Users</button>
                {{username}}
                <button type= "button" v-on:click="myAccount">My Account</button>
                <button type= "button" v-on:click="logout">Logout</button>
            </div>
            <div v-if="role === 'CUSTOMER'">
                <button type= "button" v-on:click="restaurants">Restaurants</button>
                {{username}}
                <button type= "button" v-on:click="myAccount">My Account</button>
                <button type= "button" v-on:click="logout">Logout</button>
            </div>
            <div v-if="role === 'MENAGER'">
                <button type= "button" v-on:click="restaurants">Restaurants</button>
                {{username}}
                <button type= "button" v-on:click="myAccount">My Account</button>
                <button type= "button" v-on:click="logout">Logout</button>
            </div>
            <div v-if="role === 'COURIER'">
                <button type= "button" v-on:click="restaurants">Restaurants</button>
                {{username}}
                <button type= "button" v-on:click="myAccount">My Account</button>
                <button type= "button" v-on:click="logout">Logout</button>
            </div>
            <div v-if="window === 'RESTAURANTS'">
                <allRestaurants></allRestaurants>
            </div>
            <div v-if="window === 'USERS'">
                <allUsers></allUsers>
            </div>
            <div v-if="window === 'REGISTEREMPLOYEE'">
                <registerEmployee></registerEmployee>
            </div>
            <div v-if="window === 'MYACCOUNT'">
            <myAccount></myAccount>
        </div>

        </div>
    `,
    methods:{
        register(){
            this.$router.push("/register")
        },
        login(){
            this.$router.push("/login")
        },
        restaurants(){
            this.window = "RESTAURANTS"
        },
        registerEmployee(){
            this.window = "REGISTEREMPLOYEE"
            //this.$router.push("/registerEmployee")
        },
        showUsers(){
            this.window = "USERS"
            //this.$router.push("/allUsers")
        },
        myAccount(){
            this.window = "MYACCOUNT"
            //this.$router.push("/allUsers")
        },
        logout(){
            this.window = "RESTAURANTS"
            localStorage.removeItem("id")
            localStorage.removeItem("role")
            localStorage.removeItem("username")
            this.id = ""
            this.role = ""
            this.username = ""
            window.location.reload()
            //this.$router.push("/allUsers")
        }

    }

})