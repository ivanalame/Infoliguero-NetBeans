simplyCountdown('#contador', {
    year: 2023,
    month: 6, 
    day: 14, 
    hours: 0, 
    minutes: 0, 
    seconds: 0, 
    words: { 
        days: { singular: 'Dia', plural: 'Dias' },
        hours: { singular: 'Hora', plural: 'Horas' },
        minutes: { singular: 'minuto', plural: 'Minutos' },
        seconds: { singular: 'segundo', plural: 'Segundos' }
    },
    plural: true, 
    inline: true, 
    inlineClass: 'simply-countdown-inline', 
    enableUtc: true, 
   onEnd: function() {  //con esta parte actualizo el contador cada 7 dias 
    var currentDate = new Date();
    currentDate.setDate(currentDate.getDate() + 7); 
    simplyCountdown('#contador', {
        year: currentDate.getFullYear(),
        month: currentDate.getMonth() + 1,
        day: currentDate.getDate(),
        hours: 0, 
        minutes: 0, 
        seconds: 0, 
        words: { 
            days: { singular: 'Dia', plural: 'Dias' },
            hours: { singular: 'Hora', plural: 'Horas' },
            minutes: { singular: 'minuto', plural: 'Minutos' },
            seconds: { singular: 'segundo', plural: 'Segundos' }
        },
        plural: true, 
        inline: true, 
        inlineClass: 'simply-countdown-inline', 
        enableUtc: true, 
        refresh: 1000, 
        sectionClass: 'simply-section', 
        amountClass: 'simply-amount',
        wordClass: 'simply-word',
        zeroPad: false,
        countUp: false 
    });
},

    refresh: 1000, 
    sectionClass: 'simply-section', 
    amountClass: 'simply-amount', 
    wordClass: 'simply-word', 
    zeroPad: false,
    countUp: false
});
