Vue.component("home",{

    data: function(){
        return{
            restorani:"",
        }
    },
    mounted(){

    },
    template:`
    	<div>
        	<h1>BOOTLEG DONESI</h1>
        	<button type= "button" v-on:click="register">Register</button>
        </div>
    `,
    methods:{
        register(){
            this.$router.push("/register")
        }

    }

})