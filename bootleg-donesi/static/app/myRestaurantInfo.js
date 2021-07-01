Vue.component("myRestaurantInfo",{

    data: function(){
        return{
            getFor:{
                username: "",
            },
            restaurantDTO:""
        }
    },
    mounted(){
        this.getFor.username = localStorage.getItem('username')
        axios
        .post('/getRestaurantInfo',this.getFor)
        .then(response=>{
            this.restaurantDTO = response.data
        })
    },
    template:`
    	<div>
        <h1>Information</h1>
        </br>
        </br>
        </br>
        <div>
            <table style="width:99.999%">
                <thead>
                    <th style="width:50%"></th>
                    <th style="width:50%"></th>
                </thead>
                <tbody>
                <tr class="inforow">
                    <td style="width:50%; text-align:center"><b>Logo</b></td>
                    <td style="width:50%"><img :src="restaurantDTO.logoPath" width="100" height="100" ></td>
                </tr>                        
                <tr class="inforow">
                    <td style="width:50%; text-align:center"><b> Name</b></td>
                    <td style="width:50%">{{restaurantDTO.name}} </td>
                </tr>
                <tr class="inforow">
                    <td style="width:50%; text-align:center"><b>Type</b></td>
                    <td style="width:50%">{{restaurantDTO.type}}  </td>
                </tr>
                <tr class="inforow">
                    <td style="width:50%; text-align:center"><b>Status</b></td>
                    <td style="width:50%">{{restaurantDTO.status}}  </td>
                </tr>
                <tr class="inforow">
                    <td style="width:50%; text-align:center"><b>Rating</b></td>
                    <td style="width:50%" v-if="!restaurantDTO.rating" >No rating yet</td>
                    <td style="width:50%" v-if="restaurantDTO.rating" >{{restaurantDTO.rating}}</td>
                </tr> 
                <tr class="inforow">
                    <td style="width:50%; text-align:center"><b>Location</b></td>
                    <td style="width:50%">{{restaurantDTO.location.adress.street}}, {{restaurantDTO.location.adress.town}}, {{restaurantDTO.location.adress.country}}</td>
                </tr>                                            
                </tbody>
            </table>
        </div>
        </div>

    `,
    methods:{

    }

})