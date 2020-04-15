/**
 * 时间：2018年4月6日
 * 地点：
 * 包名：com.example.demo.system.service.impl
 * 项目名：grap
 */
package com.example.demo.system.mysql.service.impl;

import com.example.demo.mq.EsCustomer;
import com.example.demo.system.mysql.dao.KgJpaRepository;
import com.example.demo.system.mysql.entity.Kg;
import com.example.demo.system.mysql.service.IKgService;
import com.example.demo.util.JsonUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Administrator
 */
@Service("kgservice")
public class KgService implements IKgService {
    private final static String KGSERVICE_ES_BEAN_NAME = "esKgServiceImpl";
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    private KgJpaRepository kgJpaRepository;
    @Resource
    private NodeService nodeSnoervice;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<Kg> findKgBychapterId(int chapterId) {
        return kgJpaRepository.findAllByChapterId(chapterId);
    }

    @Override
    public List<Kg> findAll() {
        return kgJpaRepository.findAll();
    }

    @Override
    public List<Kg> findOneBySql(String tablename, String filed, Object o) {
        String sql = "select * from " + tablename + " u WHERE u." + filed + "=?";
        // "select * from " + tablename + " u WHERE u." + filed + "=?"
        // System.out.println("sql语句：");
        // System.out.println(sql);
        Query query = entityManager.createNativeQuery(sql, Kg.class);
        query.setParameter(1, o);
        @SuppressWarnings("unchecked")
        List<Kg> list = query.getResultList();
        // System.out.println(list);
        entityManager.close();
        return list;
    }

    @Override
    public Kg save(Kg kg) {
        // TODO Auto-generated method stub
        Kg save = kgJpaRepository.save(kg);
        rabbitTemplate.convertAndSend("", EsCustomer.SAVE, JsonUtils.getEsMessage(KGSERVICE_ES_BEAN_NAME, save.toEsKg()));
        return save;
    }

    public void delete(int kgId) {
        kgJpaRepository.deleteById(kgId);
        rabbitTemplate.convertAndSend("", EsCustomer.DELETE, JsonUtils.getEsMessage(KGSERVICE_ES_BEAN_NAME, kgId));

    }

    @Transactional
    @Modifying
    @Override
    public void updateByEntiy(Kg kg) {
        Kg save = kgJpaRepository.save(kg);
        rabbitTemplate.convertAndSend("", EsCustomer.SAVE, JsonUtils.getEsMessage(KGSERVICE_ES_BEAN_NAME, save.toEsKg()));
    }

    /*
     * 测试pdf
     *
     * public static bool ConvertWord2Pdf(string sourcePath, string targetPath) {
     * bool result; Word.WdExportFormat exportFormat=
     * Word.WdExportFormat.wdExportFormatPDF; object paramMissing = Type.Missing;
     * Word.Application wordApplication = new Word.Application(); Word.Document
     * wordDocument = null; try { object paramSourceDocPath = sourcePath; string
     * paramExportFilePath = targetPath; Word.WdExportFormat paramExportFormat =
     * exportFormat; Word.WdExportOptimizeFor paramExportOptimizeFor =
     * Word.WdExportOptimizeFor.wdExportOptimizeForPrint; Word.WdExportRange
     * paramExportRange = Word.WdExportRange.wdExportAllDocument; int paramStartPage
     * = 0; int paramEndPage = 0; Word.WdExportItem paramExportItem =
     * Word.WdExportItem.wdExportDocumentContent; Word.WdExportCreateBookmarks
     * paramCreateBookmarks =
     * Word.WdExportCreateBookmarks.wdExportCreateWordBookmarks;
     *
     * wordDocument = wordApplication.Documents.Open( ref paramSourceDocPath, ref
     * paramMissing, ref paramMissing, ref paramMissing, ref paramMissing, ref
     * paramMissing, ref paramMissing, ref paramMissing, ref paramMissing, ref
     * paramMissing, ref paramMissing, ref paramMissing, ref paramMissing, ref
     * paramMissing, ref paramMissing, ref paramMissing); if (wordDocument != null)
     * wordDocument.ExportAsFixedFormat(paramExportFilePath, paramExportFormat,
     * false, paramExportOptimizeFor, paramExportRange, paramStartPage,
     * paramEndPage, paramExportItem, true, true, paramCreateBookmarks, true, true,
     * false, ref paramMissing); result = true; } finally { if (wordDocument !=
     * null) { wordDocument.Close(ref paramMissing, ref paramMissing, ref
     * paramMissing); wordDocument = null; } if (wordApplication != null) {
     * wordApplication.Quit(ref paramMissing, ref paramMissing, ref paramMissing);
     * wordApplication = null; } GC.Collect(); GC.WaitForPendingFinalizers();
     * GC.Collect(); GC.WaitForPendingFinalizers(); } return result; } /// <summary>
     * /// 将excel文档转换成PDF格式 /// </summary> /// <param name="sourcePath"></param> ///
     * <param name="targetPath"></param> /// <returns></returns> public static bool
     * ConvertExcel2Pdf(string sourcePath, string targetPath) { bool result; object
     * missing = Type.Missing; Excel.XlFixedFormatType targetType=
     * Excel.XlFixedFormatType.xlTypePDF; Excel.Application application = null;
     * Excel.Workbook workBook = null; try { application = new Excel.Application();
     * object target = targetPath; workBook = application.Workbooks.Open(sourcePath,
     * missing, missing, missing, missing, missing, missing, missing, missing,
     * missing, missing, missing, missing, missing, missing);
     * workBook.ExportAsFixedFormat(targetType, target,
     * Excel.XlFixedFormatQuality.xlQualityStandard, true, false, missing, missing,
     * missing, missing); result = true; } catch { result = false; } finally { if
     * (workBook != null) { workBook.Close(true, missing, missing); workBook = null;
     * } if (application != null) { application.Quit(); application = null; }
     * GC.Collect(); GC.WaitForPendingFinalizers(); GC.Collect();
     * GC.WaitForPendingFinalizers(); } return result; } /// <summary> ///
     * 将ppt文档转换成PDF格式 /// </summary> /// <param name="sourcePath"></param> ///
     * <param name="targetPath"></param> /// <returns></returns>
     *
     * public static boolean ConvertPowerPoint2Pdf(string sourcePath, string
     * targetPath) { boolean result; PowerPoint.PpSaveAsFileType targetFileType=
     * PowerPoint.PpSaveAsFileType.ppSaveAsPDF; PowerPoint.Application application =
     * null; PowerPoint.Presentation persentation = null; try { application = new
     * PowerPoint.Application(); persentation =
     * application.Presentations.Open(sourcePath, MsoTriState.msoTrue,
     * MsoTriState.msoFalse, MsoTriState.msoFalse); persentation.SaveAs(targetPath,
     * targetFileType, MsoTriState.msoTrue); result = true; } catch { result =
     * false; } finally { if (persentation != null) { persentation.Close();
     * persentation = null; } if (application != null) { application.Quit();
     * application = null; } GC.Collect(); GC.WaitForPendingFinalizers();
     * GC.Collect(); GC.WaitForPendingFinalizers(); } return result; }
     *
     *
     */
}
