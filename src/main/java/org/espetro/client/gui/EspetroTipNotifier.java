package org.espetro.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.esradial.client.toast.EsToasts;
import org.esradial.client.toast.ToastSpec;
import org.espetro.Espetro;

/**
 * 右侧短提示（拒绝原因 / 信息），由 EsRadial 的 {@link EsToasts} 绘制。
 * <p>
 * 停靠屏幕右侧中间，从右缘外滑入；打开部署等主界面时画在界面上层，保证拒绝原因可见。
 * 相同内容 1.5 秒内只提示一次。EsToasts 出错时降级为聊天栏消息，保证不崩溃。
 */
public final class EspetroTipNotifier {

    private static final long DEBOUNCE_MS = 1500L;

    private static String lastKey = "";
    private static long lastShownAt;

    private EspetroTipNotifier() {
    }

    public static void showDenial(String title, String body) {
        show(title, body, true, "denial:" + title + ":" + body);
    }

    public static void showInfo(String title, String body) {
        show(title, body, false, "info:" + title + ":" + body);
    }

    public static void showRaw(String message) {
        if (message == null || message.isBlank()) {
            return;
        }
        String plain = message.replace('§', '&');
        // 去掉简单格式码，作为提示正文
        String stripped = message.replaceAll("(?i)§[0-9A-FK-OR]", "");
        show("无法操作", stripped, true, "raw:" + plain);
    }

    private static void show(String title, String body, boolean error, String debounceKey) {
        long now = System.currentTimeMillis();
        if (debounceKey.equals(lastKey) && now - lastShownAt < DEBOUNCE_MS) {
            return;
        }
        lastKey = debounceKey;
        lastShownAt = now;

        Component titleText = Component.literal(title == null ? "" : title);
        Component bodyText = Component.literal(body == null ? "" : body);
        try {
            EsToasts.show(error ? ToastSpec.error(titleText, bodyText) : ToastSpec.info(titleText, bodyText));
        } catch (Throwable t) {
            Espetro.LOGGER.debug("EsToasts 提示失败，降级聊天: {}", t.toString());
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                String line = "§" + (error ? "c" : "e") + title + "§r\n§f" + body;
                mc.player.displayClientMessage(Component.literal(line), false);
            }
        }
    }
}
