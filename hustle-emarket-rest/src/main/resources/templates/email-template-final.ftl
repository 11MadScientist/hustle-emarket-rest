<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- <link rel="stylesheet" th:href="@{/css/email-template.css}"> -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <body style="margin: auto; align-items: center; text-align: center;max-width: 600px;
    min-width: 300px;font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;">
       <div class="overall-layer">
           <div class="top-layer" 
           style="background-image: linear-gradient(to top left, #033150, #04b474);height: 20vh;min-height: 70px;">
            <img 
            style="height: 7vh;
            margin-top: 5vh;
            min-height: 30px;
            max-height: 100px;
            max-width: 400px;" 
            src="http://emarket-hustle.com/api/images/emarket/2" alt="">
           </div>
           <div class="content-layer" 
           style="padding: 20px;
           margin-bottom: 10px;
           font-size: 13px;
           text-align: left;
           line-height: 23px;">
            Hello ${name}, <br><br>
            ${body}<br><br>
               <p>Sincerely Yours,<br>Emarket Team</p>

           </div>
           <div class="bottom-layer"
            style="text-align: center;
            height: 8vw;
            min-height: 70px;">
               <div style="display: inline-block;" 
               class="logo-name">
                <img 
                style="width: 16vw;
                height: 8vw;
                min-width: 100px;" 
                src="http://emarket-hustle.com/api/images/emarket/1" alt=""></div>
               <div class="border" style="display: inline-block;
               height: 100%;
               width: 2px;
               background-color: rgb(48, 47, 47);"></div>
               <div class="information-content" 
               style="width: 10v;
               display: inline-block;
               line-height: 4px;
               font-size: .8vw;
               text-align: left;">
                   <h3><em style="color: #41a8ca;">Hustle Company</em></h3>
                   <h4>Business Software Solutions</h4>
                   <p><em style="color: #41a8ca;">Phone: </em> +639323555373 <em style="color: #41a8ca;">Mobile: </em>+639323555373</p>
                   <p><em style="color: #41a8ca;">Web: </em><a target="_blank" href="http://emarket-hustle.com/">www.emarket-hustle.com</a></p>
                   <p><em style="color: #41a8ca;">Address: </em>A.C Cortes Ave, Mandaue City, 6014 Cebu</p>
                   <div class="icons" style="display: inline-block;">
                     <a href="#"><img style=" width: 20px;
                        height:20px;
                        border-radius: 5px;" class="facebook" src="https://toolboxpayment.com/wp-content/uploads/2021/06/facebook-600.png" alt=""></a>
                    <a href="#"><img style="  width: 30px;
                        height:20px;
                        border-radius: 5px;" class="youtube" src="https://martech.org/wp-content/uploads/2014/08/youtube-logo-1920.jpg" alt=""></a>
                   </div>
               </div>
           </div>
       </div>
    </body>
</html>