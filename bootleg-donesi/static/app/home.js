Vue.component("home",{

    data: function(){
        return{
            restorani:null,
            user:"",
            role:"",

        }
    },
    mounted(){
        this.user = localStorage.getItem('user')
        if(this.user){
            this.role = this.user.role
        }
    },
    template:`
        <div>
    	    <div v-if="(!role)" >
            	<h1>BOOTLEG DONESI</h1>
            	<button type= "button" v-on:click="register">Register</button>
            	<button type= "button" v-on:click="login">Login</button>
            </div>
            <div>
                <h1>DOBRODOSO GAZDA</h1>
                <button type= "button" v-on:click="registerEmployee">Register Employee</button>
                <button type= "button" v-on:click="showUsers">Users</button>
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
        registerEmployee(){
            this.$router.push("/registerEmployee")
        },
        showUsers(){
            this.$router.push("/allUsers")
        }

    }

})