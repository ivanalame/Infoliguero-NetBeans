const app = Vue.createApp({
    setup() {
        const title = "Infoliguero";
        const dos = "La Liga";
        return{
            title,dos,
        };
    },
});

const liga = Vue.createApp({
    setup() {
      
        const laliga = "Liga Santander";
        const premier = "Premier League";
        const bundesliga = "Bundesliga";
        const serie = "Serie A";
        const ligue = "Ligue 1";
        return{
            laliga,premier,bundesliga,serie,ligue,
        };
    },
});