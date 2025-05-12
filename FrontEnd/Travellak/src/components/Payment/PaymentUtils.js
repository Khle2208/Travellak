// paymentUtils.js

export const handlePayment = async (totalPrice, bookingId) => {
  try {
    const token = localStorage.getItem("token");
    if (!token) {
      alert("Token không tồn tại, vui lòng đăng nhập lại");
      return;
    }
    const cleanReturnUrl = window.location.href.split("?")[0];
    const paymentResponse = await fetch(
      `http://localhost:8080/Travellak/create_payment`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          totalPrice: totalPrice,
          returnUrl: cleanReturnUrl,
          bookingId: bookingId,
        }),
      }
    );

    if (!paymentResponse.ok) {
      throw new Error("Failed to create payment");
    }

    const data = await paymentResponse.json();
    const paymentUrl = data.paymentUrl;

    // Điều hướng người dùng đến paymentUrl để thanh toán
    window.location.href = paymentUrl;
  } catch (error) {
    console.error("Error:", error);
    alert(error.message || "An error occurred during payment");
  }
};
