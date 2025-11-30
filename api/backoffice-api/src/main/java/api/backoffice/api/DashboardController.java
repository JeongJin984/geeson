package api.backoffice.api;

import app.backoffice.application.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import support.enums.PaymentStatus;
import support.enums.ProductStatus;
import support.enums.RefundStatus;
import support.enums.SettlementStatus;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {
    private final OrderSelectApp orderSelectApp;
    private final PaymentSelectApp paymentSelectApp;
    private final SettlementSelectApp settlementSelectApp;
    private final RefundSelectApp refundSelectApp;
    private final ProductSelectApp productSelectApp;
    private final ReviewSelectApp reviewSelectApp;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("adminName", "관리자");

        model.addAttribute("totalOrders", orderSelectApp.totalOrderCount(null, null, null));
        model.addAttribute("todayOrders", orderSelectApp.totalOrderCount(
            LocalDate.now().atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay(), null
        ));
        model.addAttribute("totalCompletedAmount", paymentSelectApp.sumPaymentAmount(null, null, List.of(PaymentStatus.SUCCESS)));
        model.addAttribute("pendingSettlements", settlementSelectApp.countSettlement(null, null, List.of(SettlementStatus.PENDING)));
        model.addAttribute("refundRequestedCount", refundSelectApp.countRefund(null, null, List.of(RefundStatus.REQUEST)));
        model.addAttribute("todayPaymentErrorCount", paymentSelectApp.countPaymentAmount(
            LocalDate.now().atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay(), PaymentStatus.getErrorStatus()
        ));
        model.addAttribute("activeProductCount", productSelectApp.countProduct(true, List.of(ProductStatus.SELLING)));
        model.addAttribute("todayReviewCount", reviewSelectApp.countReview(
            LocalDate.now().atStartOfDay(), LocalDate.now().plusDays(1).atStartOfDay()
        ));

        model.addAttribute("recentOrders", orderSelectApp.getOrders(null, null, null, 0, 10));
        model.addAttribute("recentPayments", paymentSelectApp.getPayments(null, null, null, 0, 10));
        model.addAttribute("lowStockVariants", productSelectApp.getLowStockProducts(true, null, 0, 10));
        model.addAttribute("recentRefunds", refundSelectApp.getRefunds(null, null, null, 0, 10));

        return "admin/dashboard";
    }
}
