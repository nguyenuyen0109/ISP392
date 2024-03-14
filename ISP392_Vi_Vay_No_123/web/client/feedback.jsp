<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap' rel='stylesheet'>
        <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="../assets/css/feedback.css"> <!-- Link to your CSS file -->
        <title>Form Reviews</title>
    </head>
    <body>
        <div class="wrapper">
            <h3>Feedback</h3>
            <form action="/ISP392_Vi_Vay_No_123/feedback" method="POST">
                <div class="rating">
                    <input type="number" name="rating" hidden>
                    <i class='bx bx-star star' style="--i: 0;"></i>
                    <i class='bx bx-star star' style="--i: 1;"></i>
                    <i class='bx bx-star star' style="--i: 2;"></i>
                    <i class='bx bx-star star' style="--i: 3;"></i>
                    <i class='bx bx-star star' style="--i: 4;"></i>
                </div>
                <textarea name="opinion" cols="30" rows="5" placeholder="Your opinion..."></textarea>
                <div class="btn-group">
                    <button type="submit" class="btn submit">Submit</button>
                    <button class="btn cancel">Cancel</button>
                </div>
            </form>
        </div>
        <script src="../assets/js/feedback.js"></script>
    </body>
</html>
