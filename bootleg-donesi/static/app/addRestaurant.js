Vue.component("addRestaurant",{

    data: function(){
        return{
            restaurant:{
                name:"",
                type:"",
                status:"",
                location:{
                    geoLongitude:123,
                    geoLatitude:123,
                    adress:{
                        street:"",
                        town:"",
                        postalCode:"",
                    }
                },
                logoPath:"",
                menuItems:"",
                rating:"",
            }
        }
    },
    mounted(){

    },
    template:`
    	<div>
        	<h1>New restaurant registration form</h1>
            <form id="registrationFormRest" method ="POST" @submit.prevent = "register">

                <div>
                    <label for="name"><b>Name</b></label>
                    <input type="text" v-model="restaurant.name" placeholder = "Name" required/>
                </div>

                <div>
                    <label for="type"><b>Type</b></label>
                    <select name="type" v-model="restaurant.type" id="type" required>
                        <option value="ITALIAN">Italian</option>
                        <option value="CHINESE">Chinese</option>
                        <option value="GRILL">Grill</option>
                        <option value="MEXICAN">Mexican</option>
                        <option value="GREEK">Greek</option>
                    </select>
                </div>

                <div>
                    <label for="location"><b>Location</b></label> </br>
                    <label for="street">Street</label>
                    <input type="text" v-model="restaurant.location.adress.street" placeholder = "Street" required/>
                    <label for="town">Town</label>
                    <input type="text" v-model="restaurant.location.adress.town" placeholder = "Town" required/>
                    <label for="postalCode">Postal Code</label>
                    <input type="text" v-model="restaurant.location.adress.postalCode" placeholder = "Postal Code" required/>
                </div>

                <div>
                    <button type = "submit"> Register </button>
                </div>

            </form>
        </div>
    `,
    methods:{
        register(){
        	console.log(this.restaurant)
            
        },

    }

})